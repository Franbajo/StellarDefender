package ui;
@FunctionalInterface
//Esta funcional interface nos va a servir para implementar el m�todo LAMBDA de la clase boton
public interface Action {
    public abstract void doAction(int position);
}
