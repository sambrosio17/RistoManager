import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import it.RistoManager.utils.CodeGenerator;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		CodeGenerator generator=new CodeGenerator();
		while(true) {
			System.out.println(generator.generate());
			Thread.sleep(3000);
		}

	}

}
