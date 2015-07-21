//Import Sikuli script
import org.sikuli.script.*;

//Import JUnit
import org.junit.*;

public class Testing
{
	//Sikuli script object
	private SikuliScript m_sikscr;

	//Computer screen object
	private Screen m_screen;
	
	//Image of Firefox address bar
	private Pattern m_address;
	
	//Image of Firefox go image
	private Pattern m_go;
	
	//Image of Yahoo ID label
	private Pattern m_yid;
	
	//Image of Yahoo password label
	private Pattern m_pass;
	
	//Image of Yahoo Signin button
	private Pattern m_signin;
	
	//Image of Yahoo brand name
	private Pattern m_logo;
	
	//Constructor
	public Testing()
	{
		//Load images from files
		m_address = new Pattern("./img/FirefoxBar.png");
		m_go = new Pattern("./img/FirefoxGo.png");
		m_yid = new Pattern("./img/YahooID.png");
		m_pass = new Pattern("./img/Password.png");
		m_signin = new Pattern("./img/SignIn.png");
		m_logo = new Pattern("./img/Logo.png");

		//Create Sikuli script and screen objects
		try
		{
			m_sikscr = new SikuliScript();
			m_screen = new Screen();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//This method is invoked before JUnite test case executes
	@ Before
	public void setUp()
	{
		//Launch Firefox
		m_sikscr.openApp("Full path\\firefox.exe");
		
		//Wait a bit
		m_screen.wait((double) 3.0);

		try
		{
			//Click Firefox address bar then type address
			m_screen.type(m_address, "http://mail.yahoo.com");
			
			//Sikuli Click the go button
			m_screen.click(m_go);
			
			//Wait a bit
			m_screen.wait((double) 3.0);
						
			//Find login label 
			Match login = m_screen.exists(m_yid.similar((float)0.50));
		    
			//Sikuli Click below the login label
			m_screen.click(login.below(10));
		    
			//CTRL-A to select all text
			m_screen.type("a", KeyModifier.CTRL);
	    	
			//Type user name
			m_screen.type("my_yahoo_id");

			//Find the password label
			Match pass = m_screen.exists(m_pass.similar((float)0.70));
		    
			//Sikuli Click below the password label
			m_screen.click(pass.below(10));
	    	
			//Select all text
			m_screen.type("a", KeyModifier.CTRL);
	    	
			//Enter password
			m_screen.type("my_yahoo_password");
	    	
			//Sikuli Click the sign in button
			m_screen.click(m_signin);
	        
			//Wait a bit
			m_screen.wait((double) 3.0);   
		}
		catch (FindFailed e)
		{
			e.printStackTrace();
		}
	}

	//This method is invoked after JUnit test case is executed
	@ After
	public void tearDown() 
	{
		//Close Firefox app
		m_sikscr.closeApp("Mozilla Firefox");
	}

	//Test case checks if Yahoo logo exists after login
	@Test
	public void testLogo() throws Exception 
	{
		//Make sure Yahoo brand name exists
		assert(m_screen.exists(m_logo)!=null);
	    
		//Wait a bit
		m_screen.wait((double) 3.0);
	}

}
