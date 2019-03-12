package org.github.davidcana.jcrud.core.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * <p>
 *   Generates unique ids using 3 groups of hex chars:
 * </p>
 * 
 * <ol>
 *   <li>(0-7) IPAddress as HEX: 8 bytes.</li>
 *   <li>(8-19) CurrentTimeMillis() as HEX: Display all 12 bytes.</li>
 *   <li>(20-27) SecureRandom() as HEX: Keep 8 significant bytes.</li>
 * </ol>
 * 
 * @since 0.1
 * @author <a href="mailto:david.cana@gmail.com">David Cana Lopez</a>
 */
public class RandomGenerator {

    //  ----------------------------------------------------- Manifest constants
    //private static final char SEGMENT_SEPARATOR = '-';
    //private static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
    private static final int RANDOM_SEGMENT_LENGTH = 10;
    
    
    //  ----------------------------------------------------------- Data members
    private static RandomGenerator instance;
    private Random random = new Random();
    
    //  ----------------------------------------------------------- Constructors
    
    /**
     * Empty constructor
     *
     */
    private RandomGenerator(){ }
    

    // --------------------------------------------------------------- Methods
    
    public int generateInteger(int size){
    	
		int result = this.random.nextInt(size);

	    return result;
    }
    


    public String generateString(){
        return this.generateString(RANDOM_SEGMENT_LENGTH);
    }
    /*
    private String generateOid(boolean currentTimeSegment, boolean randomSegment) {
        
        StringBuffer result = new StringBuffer();
        
        if (currentTimeSegment){
            
            if (result.length() > 0){
                result.append(SEGMENT_SEPARATOR);
            }
            
            result.append(generateCurrentTimeSegment());
        }
        
        if (randomSegment){
            
            if (result.length() > 0){
                result.append(SEGMENT_SEPARATOR);
            }
            
            result.append(generateRandomSegment(null));
        }

        
        return result.toString().toUpperCase();
    }*/


    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    
    public String generateString( int len ){
    	   StringBuilder sb = new StringBuilder( len );
    	   for( int i = 0; i < len; i++ ) 
    	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
    	   return sb.toString();
    }
    /*
    public String generateString(int length) {
        
        try {
			SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM);
			
			String result = Long.toHexString(secureRandom.nextLong());
			
			while (result.length() < length) {
			    result = result + Long.toHexString(secureRandom.nextLong());
			}
			
			return result.substring(0, length);
			
		} catch (NoSuchAlgorithmException e) {
			return "This must not occur!";
			// This must not occur!
		}
    }*/


    /**
     * Return the current time segment of the id
     * 
     * @return the current time segment of the id
     */
    /*
    static private String generateCurrentTimeSegment() {
        
        String result = Long.toHexString(System.currentTimeMillis());
        
        while (result.length() < 12){
            result = '0' + result;
        }
        
        return result;
    }*/
    
    
    /**
     * Get an instance of this class, create one if needed
     * 
     * @return the instance
     */
    public static RandomGenerator getInstance(){
    
        if (instance == null){
            instance = new RandomGenerator();
        }
        
        return instance;
    }
    
}
