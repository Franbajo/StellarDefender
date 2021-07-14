
package math;

public class Vector2D {
    private double x, y;
    public Vector2D(double x, double y){
        this.x=x;
        this.y=y; 
    }
    public Vector2D(){
        x=0;
        y=0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    /*....MÉTODOS MATEMÁTICOS NECESARIOS PARA EJECUTAR LA FÍSICA DEL JUEGO...*/
    public Vector2D add(Vector2D v)
    {
            return new Vector2D(x + v.getX(), y + v.getY());
    }
    public Vector2D subtract(Vector2D v)
    {
            return new Vector2D(x - v.getX(), y - v.getY());
    }
    public double getMagnitude()
    {
		return Math.sqrt(x*x + y*y);
    }
            /*Estos tres métodos normalize, limit y scale solo los aplico al OJO*/

    public Vector2D scale(double value)
	{
		return new Vector2D(x*value, y*value);
	}
    
    //Sino ponemos esto el OJO sale disparado a toda leche
    public Vector2D limit(double value)
    {
            if(getMagnitude() > value)
            {
                    return this.normalize().scale(value);
            }
            return this;
    }
    //Para complementar el limit hay que crear un normalizador
        public Vector2D normalize()
    {
		double magnitude = getMagnitude();
		
		return new Vector2D(x / magnitude, y / magnitude);
    }
   /*......................................................................*/
}
