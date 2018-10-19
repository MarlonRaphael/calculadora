package br.edu.up.calculadora_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView txtVisor; // text view que representa o visor da calculadora

    Calculadora calc; // objeto do tipo Calculadora
    boolean digitando; // verifica se usuário está digitando ou não

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referenciando o elemento da interface (View) chamado txtVisor
        txtVisor = findViewById(R.id.txtVisor);
        // novo objeto do tipo Calculadora
        calc = new Calculadora();

        // neste momento, o usuário não está digitando
        digitando = false;

        // texto inicial do visor é 0 (zero)
        txtVisor.setText("0");

    }// fim onCreate

    public void onClickNumeros(View view){

        // recebe um elemento da interface e o converte para o tipo botão
        Button btnValor = (Button)view;

        // recebe a string dentro de txtVisor e armazena numa variável local
        String textoVisor = txtVisor.getText().toString();

        // se o texto do visor for diferente de zero E o usuário estiver digitando:
        if(!textoVisor.equals("0") && digitando) {

            // string 'textoVisor' recebe seu próprio valor + string do botao digitado (o número)
            textoVisor = textoVisor + btnValor.getText().toString();

            // seta novamente o texto do visor, passando o novo valor
            txtVisor.setText(textoVisor);

            /* Este processo acima serve para juntarmos os algarismos digitados para formarmos
                diferentes números.

                Então se, por exemplo, o usuário quiser digitar 35, ele irá pressionar a tecla 3,
                e a tecla 5. Para o nosso código formar e exibir o número 35, devemos concatenar
                o primeiro valor pressionado (3), com o segundo valor pressionado (5).

                A medida que formamos novos números com a combinação dos algarismos digitados,
                devemos substituir a string que exibe o valor no visor da calculadora pelo novo
                número formado.
             */

        }else{// senão...

            // Se a string do botão pressionado for igual a 0 (zero)
            if(btnValor.getText().toString().equals("0")){

                // setaremos sempre o texto para 0 (zero)
                txtVisor.setText("0");

                /*
                A linha acima garante que, se a calculadora estiver apresentnado o valor 0 na tela
                e pressionarmos a tecla 0 (zero), o número que será indicado no visor permanecerá
                sendo zero.

                Isso é para evitar a calculadora de exibir, por exemplo, o valor 00000 caso a tecla
                tenha sido pressionada 5 vezes
                 */
            }
            else // senão...
            {
                // setamos o valor do visor para o valor presente na string do botão pressionado
                textoVisor = btnValor.getText().toString();
                txtVisor.setText(textoVisor);
                // nesse momento, dizemos para o programa que o usuário está digitando um valor,
                // sendo que este valor poderá ser composto por mais de um algarismo.
                digitando = true;
            }
        }

    }// fim onClickNumeros

    public void onClickOperacoes(View view){

        // converte elemento da interface para um botão
        Button btnOp = (Button)view;
        // armazena em variavel local o texto do visor
        String textoVisor = txtVisor.getText().toString();


        // Ao clicar no botão de qualquer operação, a calculadora precisa entender que o usuário
        // já informou o primeiro número e a operação que deseja realizar.
        // portanto, nesse momento, o usuário não está mais ditando um número.
        digitando = false; // necessário para, posteriormente, o usuário digitar o segundo número

        // converte o texto do visor para um valor double
        Double valorVisor = Double.parseDouble(textoVisor);

        // seta o atributo 'valor' do objeto calc com o valor convertido
        calc.setValor(valorVisor);
        // manda o objeto calc verificar qual é a operação
        calc.verificarOperacao(btnOp.getText().toString());

        // extrai o valor double retornado de calc.getValor e o converte para uma string
        String textoResultado = String.valueOf(calc.getValor());

        // se esse valor double terminar com .0
        if(textoResultado.endsWith(".0")){

            // formatar o texto com o valor que será visualizado de modo que apareça apenas a
            // parte inteira. Ou seja, se o resultado for 3.0, o visor deverá mostrar apenas 3

            // para que isso seja possível, basta subtrair dois caracteres da string que armazena
            // o valor que deverá ser exibido na tela.
            textoResultado = textoResultado.substring(0, textoResultado.length()-2);
        }

        // seta o texto do visor com o novo valor
        txtVisor.setText(textoResultado);

    }
}
