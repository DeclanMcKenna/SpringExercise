package config;

import org.springframework.context.annotation.*;

import core.Reader;

@Configuration
public class AppConfig 
{	
	@Bean
	public Reader reader()
	{
		return new Reader();
	}
	
}//end class AppConfig
