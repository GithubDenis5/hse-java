public class ComplexNumber { // a + b * i
    float realPart; // real number a
    float imaginaryPart; // imaginary number b*i

    ComplexNumber(float a, float b) {
        realPart = a;
        imaginaryPart = b;
    }

    ComplexNumber(float a) {
        realPart = a;
        imaginaryPart = 0;
    }

    public ComplexNumber add(ComplexNumber c) {
        return new ComplexNumber(this.realPart + c.realPart, this.imaginaryPart + c.imaginaryPart);
    }

    public ComplexNumber subtract(ComplexNumber c) {
        return new ComplexNumber(this.realPart - c.realPart, this.imaginaryPart - c.imaginaryPart);
    }

    public ComplexNumber multiplication(ComplexNumber c) {
        return new ComplexNumber(this.realPart * c.realPart - this.imaginaryPart * c.imaginaryPart, this.realPart * c.imaginaryPart + this.imaginaryPart * c.realPart);
    }

    public void printComplex(int flag_ln) {
        if (flag_ln == 0) { //without \n
            if (imaginaryPart != 0) {
                System.out.print(realPart + " + " + imaginaryPart + "i");
            } else {
                System.out.print(realPart);
            }
        } else {
            if (imaginaryPart != 0) {
                System.out.println(realPart + " + " + imaginaryPart + "i");
            } else {
                System.out.println(realPart);
            }
        }

    }
}
