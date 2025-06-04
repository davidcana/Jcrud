"use strict";
//debugger;

// Get urlVars
var getUrlVars = function() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
    function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
var urlVars = getUrlVars();

// Set options
var type = urlVars[ 'type' ];
var options = constants.options[ type ];
if ( ! options ){
	alert( 'Unknown type:' + type );
	exit;
}

// Set language
var lang = urlVars[ 'lang' ] || 'en';
options.i18n.language = lang;

// Continue...
options.filesPathPrefix = constants.WEB_ROOT;

var $consolePre = $( '#consolePre' );
var $consolePost = $( '#consolePost' );
var $replace = $( '#replace' );
var $autoScroll = $( '#autoScroll' );
var preCounter = 0;
var postCounter = 0;

var $console1 = $( '#console1' );
var talking = {
	items: []
};
var currentCommand = undefined;
var currentCommandId = undefined;
var requests = {
	"listRecords": "listRequest",
	"getRecord": "getRequest",
	"batchUpdate": "updateRequest"
};
var responses = {
	"listRecords": "listResponse",
	"getRecord": "getResponse",
	"batchUpdate": "updateResponse"
};

var $copyMesssage = $( '#copyMessage' );

var appendTextToConsole = function( textToAppend, $console ){
	
	if ( $replace.prop( 'checked' ) ){
		$console.text( textToAppend + '\n' );
		return;
	}
	
	var currentText = $console.text();
	$console.text( currentText + textToAppend + '\n' );
	
	if ( $autoScroll.prop( 'checked' ) ){
		//$console.scrollTop( $console[0].scrollHeight );
		scrollTop( $console[0] );
	}
};

var appendDataToConsole = function( data, $console, counter ){
	
	appendTextToConsole(
			'[' + counter + ']'
			+ ' -> '
			+ JSON.stringify( data, null, '    ' ),
			$console
	);
};

var scrollTop = function ( el ) {
	el.scrollTo( 0, el.scrollHeight );
}

var updateConsole1 = function(){
	
	$console1.text( 
			JSON.stringify( talking, null, '    ' )
	);
	
	if ( $autoScroll.prop( 'checked' ) ){
		//$console1.scrollTop( $console1[0].scrollHeight );
		scrollTop( $console1[0] );
	}
};

options.ajax.ajaxPreFilter = function( data ){
	
	if ( $replace.prop( 'checked' ) ){
		talking.items = [];
	}
	
	currentCommand = {};
	currentCommandId = data.command;
	currentCommand[ requests[currentCommandId] ] = data;
	talking.items.push( currentCommand );
	
	appendDataToConsole( data, $consolePre, ++preCounter );
	return JSON.stringify( data );
};
options.ajax.ajaxPostFilter = function( data ){
	
	currentCommand[ responses[currentCommandId] ] = data;
	updateConsole1();
	
    appendDataToConsole( data, $consolePost, ++postCounter );
    return data;
};
options.ajax.ajaxFunction = function( options ){
	
	//
	var url = options.url;
	var json = options.data;
	var successCallback = options.success;
	var errorCallback  = options.error;
	/*
	const requestOptions = {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/x-www-form-urlencoded',
			'Accept': 'application/json'
		},
		'body': data
	};
	*/
	const requestOptions = {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		'body': json
	};
	/*
	dataType   : 'json',
	contentType: 'application/json; charset=UTF-8',
	type       : 'POST'
	*/
	fetch(
		url,
		requestOptions
	).then(
		function( response ){
			if ( ! response.ok ){
				//runErrorCallback( errorCallback, response );
				errorCallback( response );
				return;
			}
			return response.json();
		}
	).then(
		function( data ){
			if ( data ){
				successCallback( data );
			}
		}
	).catch(
		function( error ){
			//runErrorCallback( errorCallback, error );
			errorCallback( error );
		}
	);
};

var openTab = function(evt, id) {
	
	// Declare all variables
	var i, tabcontent, tablinks;
	
	// Get all elements with class="tabcontent" and hide them
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	}
	
	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	
	// Show the current tab, and add an "active" class to the button that opened the tab
	document.getElementById(id).style.display = "block";
	evt.target.className += " active";
} 

var copyConsole1ToClipboard = function(){
	
	$console1.select();
	document.execCommand( 'copy' );
	$copyMesssage.text( 'Textarea copied to clipboard!' )
		.fadeOut(500).fadeIn(500)
		.fadeOut(500).fadeIn(500)
		.fadeOut(500).fadeIn(500)
		.fadeOut(500);
};

// Get the element with id="defaultOpen" and click on it
$('.defaultTabLink').trigger( 'click' );

$( '#container' ).zcrud( 
    'init',
    options,
    function( options ){
    	var isForm = type == 'form';
        $( '#container' ).zcrud( 
        		isForm? 'renderForm': 'renderList'
        );
    }
);
