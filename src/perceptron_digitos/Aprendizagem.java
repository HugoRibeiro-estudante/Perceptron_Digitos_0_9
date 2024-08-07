package perceptron_digitos;
/**
 *
 * @author Clarimundo
 */
public class Aprendizagem {
    
    private double x[][] = {
        {1,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},   //0
        {1,0,1,0,1,1,0,0,1,0,0,1,0,1,1,1},   //1
        {1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},   //2
        {1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1},   //3
        {1,1,0,1,1,0,1,1,1,1,0,0,1,0,0,1},   //4
        {1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1},   //5
        {1,1,1,1,1,0,0,1,1,1,1,0,1,1,1,1},   //6
        {1,1,1,1,0,0,1,0,0,1,0,0,1,0,0,1},   //7
        {1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1},   //8
        {1,1,1,1,1,0,1,1,1,1,0,0,1,1,1,1}    //9
    };

    private double w[][] = new double[4][16]; // Pesos
    private double t[][] = {
        {-1, -1, -1, -1},  //0
        {-1, 1, -1, -1},   //1
        {-1, -1, 1, -1},   //2
        {-1, 1, 1, -1},    //3
        {-1, 1, 1, 1},     //4
        {1, -1, -1, -1},   //5
        {1, 1, -1, -1},    //6
        {1, -1, 1, -1},    //7
        {1, 1, 1, -1},     //8
        {1, 1, 1, 1}       //9
    };
    private int epocas;

    public Aprendizagem() {
        epocas = 0;
    }

    public double[][] getW() {
        return w;
    }

    public double[][] gett() {
        return t;
    }

    public int getepocas() {
        return epocas;
    }

    public double somatorio(int i, int perceptron) {
        double yent = 0;
        for (int j = 0; j < 16; j++) {
            yent = yent + x[i][j] * w[perceptron][j];
        }
        return yent;
    }

    public double saida(double yent, double limiar) {
        double f;
        if (yent > limiar)
            f = 1;
        else if (yent < -limiar)
            f = -1;
        else
            f = 0;
        return f;
    }

    public void atualiza(double alfa, double f[], int i, int perceptron) {
        for (int j = 0; j < 16; j++) {
            w[perceptron][j] = w[perceptron][j] + alfa * (t[i][perceptron] - f[perceptron]) * x[i][j];
        }
    }

    public void algoritmo(double alfa, double limiar) {
        double yent;
        double f[] = new double[4];
        boolean mudou;

        for (int perceptron = 0; perceptron < 4; perceptron++) {
            for (int j = 0; j < 16; j++) { 
                w[perceptron][j] = 0; // zerando pesos
            }
        }

        do {
            mudou = false;
            for (int i = 0; i < 10; i++) { //(0 a 9)
                for (int perceptron = 0; perceptron < 4; perceptron++) {
                    yent = somatorio(i, perceptron);
                    f[perceptron] = saida(yent, limiar);
                    if (f[perceptron] != t[i][perceptron]) {
                        mudou = true;
                        atualiza(alfa, f, i, perceptron);
                    }
                }
            }
            epocas++;
        } while (mudou);
    }
}

