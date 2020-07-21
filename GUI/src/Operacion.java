import java.util.Random;

public class Operacion {
	
	private String operacion;
	private final String[] operandos = {"+", "-", "*", "/"};
	private int[] numeros;
	
	public Operacion(int[] numeros) {
		//generate random operation
		this.numeros = numeros;
		this.operacion = "(" + operandos[new Random().nextInt(operandos.length)] + " " +
		numeros[new Random().nextInt(numeros.length)] + " " + numeros[new Random().nextInt(numeros.length)] + ")";
	}
	
	public String getOperacion() {
		return operacion;
	}


	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public double resolver() {
		double num1, num2;
		switch(this.operacion.charAt(1)) {
		case '+':
			num1 = Integer.parseInt("" + this.operacion.charAt(3));
			num2 = Integer.parseInt("" + this.operacion.charAt(5));
			return num1 + num2;
		case '-':
			num1 = Integer.parseInt("" + this.operacion.charAt(3));
			num2 = Integer.parseInt("" + this.operacion.charAt(5));
			return num1 - num2;
		case '*':
			num1 = Integer.parseInt("" + this.operacion.charAt(3));
			num2 = Integer.parseInt("" + this.operacion.charAt(5));
			return num1 * num2;
		case '/':
			num1 = Integer.parseInt("" + this.operacion.charAt(3));
			num2 = Integer.parseInt("" + this.operacion.charAt(5));
			if (num2 == 0) {
				return 0;
			}
			return num1 / num2;
		default:
			return 0;		
		}
	}

}
