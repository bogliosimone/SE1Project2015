package it.polimi.ingsw.bogliobresich;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    
    
	@BeforeClass
	public static void begin(){
		//System.out.println("Begin");
	}
	
	@Before
	public void prepare(){
		//System.out.println("Prepare");
	}
	
	@After
	public void finalize(){
		//System.out.println("Finalize");
	}
	
	@AfterClass
	public static void end(){
		//System.out.println("End");
	}
}
