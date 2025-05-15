import Cifra.Mecanismo;

public class App {
    public static void main(String[] args) {
        Mecanismo mec = new Mecanismo();
        mec.LoadPrimaryBuffer();
        mec.executarCripto();
        mec.executarDecripto();
    }
}