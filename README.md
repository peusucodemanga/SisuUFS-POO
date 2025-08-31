# Trabalho final da disciplina <br> Programação Orientada a Objetos:

&emsp; Esse projeto tem o intuito de abordar os conhecimentos ministrados na Universidade Federal de Sergipe acerca da OO (Orientação a objetos) na prática, onde nos foi proposto, pelo professor Leonardo Nogueira Matos, o desenvolvimento de uma aplicação em duas linguagens de programação distintas, Java e outra a critério da equipe. Essa dupla optou por desenvolvê-la em Python! 

## Integrantes da equipe:
Dimitri Martins Oliveira <br>
João Pedro Ferreira da Cruz

## Descrição do tema: Uma Análise exploratória dos dados do SISU da UFS
&emsp; A ideia principal do projeto é criar duas interfaces que sejam capazes de apresentar dados extraídos diretamente de arquivos CSV da folha de aprovados do SiSU/UFS de anos anteriores.

&emsp; Ademais, a criação de gráficos expositivos foi sugerida pelo docente como uma ferramenta de explorar a capacidade das linguagens de demonstrar como os dados podem ser interpretados e visualizados. Vistos da mesma forma em diferentes linguagens.

## Discussão do projeto desenvolvido:
&emsp; O primeiro passo para a análise dos dados é tratá-los. Após a separação dos PDFs e a conversão para ler o arquivo CSV escolhido, urgiu a necessidade da criação de uma interface gráfica simples, tanto para tratar as questões de I/O com o usuário, quanto para exibição dos gráficos formatados. 

&emsp; Para a linguagem Java, foi utilizada a biblioteca JavaFX, aliado ao SceneBuilder. Já para a linguagem Pyton, a biblioteca escolhida foi a PyQt5, juntamente ao Qt Designer. Ambas as bibliotecas sugeridas e abordadas em aula pelo docente. Quanto às IDEs, foram utilizados o Apache NetBeans e o VisualStudio Code.

##### Janelas criadas

&emsp; A aplicação desenvolvida conta com três janelas principais. Tendo como o mesmo raciocínio em Java e em Python. Durante a apresentação dessas janelas, serão utilizados prints da aplicação em Java, mas o resultado em Python é bem similar.


&emsp; Inicialmente, a interface tem uma tela de entrada, composta por um campo de digitação, uma área de exibição dos dados lidos e um botão que ativa o leitor de CSV, leitor esse que facilita a leitura de arquivos e a confirmação dos mesmos. A ação disparada pelo botão lê o arquivo, armazena os dados dos candidatos, exibe-os na tela e instancia a segunda janela. 

![Exibição da Janela Principal](./imagensMD/janela1.png "Janela Principal")

&emsp; Em seguida, surge a segunda janela, que disponibiliza três opções ao usuário:
- Um botão para consultar a maior e menor notas, que aparecem diretamente abaixo do botão;
- Um botão para gerar um gráfico de setores da quantidade de pessoas por centro;
- Um botão para gerar um histograma, filtrado por uma caixa de seleção, acerca dos intervalos de notas dos candidatos.

&emsp; Ambos os botões que criam gráficos são responsáveis por exibir a terceira janela.

![Exibição da Segunda Janela](./imagensMD/janela2.png "Segunda Janela")

&emsp; A terceira janela é a responsável por exibir os gráficos, no caso da aplicação em Java, pode existir uma quarta janela, quando ambos os gráficos são exibidos, mas em Python, apenas uma é ativa por vez.

![Gráfico de pizza e Histograma](./imagensMD/graficos_java.png "Gráficos")

##### Dificuldades encontradas
&emsp; Alguns pontos importantes e que se mostraram desafiadores durante o desenvolvimento do projeto foram os controladores das cenas, inicialmente gerados pelos respectivos builders de cada GUI, mas que posteriormente são especializados para atenderem a necessidades específicas. 

&emsp; Outra dificuldade recorrente foi o encaminhamento de dados entre arquivos/janelas diferentes, uma solução em Java foi a utilização de um padrão de projeto Singleton, mas em Python a solução encontrada foi lidar com o armazenamento de variáveis em um arquivo e operar a UI e todo o arranjo visual em classes separadas, utilizando imports para o arquivo principal.   

## Discussão da Orientação a Objetos em Python:
&emsp; A linguagem escolhida, Python, não é uma linguagem estritamente Orientada a Objetos, porém, podemos utilizar de artificios da mesma para exprimir e se manter as características desse paradigma discutido por todo esse projeto. 

&emsp; Para destacar as diversas áreas abordadas será seguida a ordem em que as classes - estruturas fundamentais em OO - são chamadas e os seus determinados objetivos.

&emsp; Inicialmente a classe MainWindow é chamada para apresentar a primeira janela supracitada e logo nela podemos ver características intrínsecas ao assunto abordado. 

```py

class MainWindow(QtWidgets.QMainWindow, Ui_MainWindow):

    def __init__(self, *args, obj=None, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle("Leitor de CSV 8000")
```
&emsp; Na primeira operação do código é imperativo denotar a herança sendo utilizada no "MainWindow", tendo como pai a classe "QtWidgets.QMainWindow", dessa maneira herdando as suas propriedades.

&emsp; Em segundo lugar, temos o método "_\_\_init\_\__" que funciona como um construtor para essas classes, nesse caso, ela trabalha com a construção da janela. E ao mesmo tempo, atuando como uma sobrescrição, pois, a classe pai também possui o método "_\_\_init\_\__". 

&emsp; Ainda analisando o método "_\_\_init\_\__", também há a presença do encapsulamento - um dos pilares da OO -, por meio de pseudo-modificadores de visibilidade \_, que, nesse caso, simbolizam um método privado. Contudo, esses modificadores não garantem que um método ou atributo realmente não poderá ser acessado onde não deveria.
                           
&emsp; Um detalhe curioso nesse trecho de código é a presença de herança múltipla de classes concretas, mecanismo permitido apenas com o uso de interfaces em Java. Python trata essa questão com o conceito de ordem de resolução múltipla (MRO), responsável por definir uma hierarquia entre as superclasses.  

TRATAMENTO DE EXCESSOES
```py
    try:
        with open(arquivo, 'r', newline='') as file:
            leitorCSV = csv.reader(file)
            Cabecalho = next(leitorCSV)
            global aprovados 
            aprovados = []
    ...
    except FileNotFoundError:
        msg = QtWidgets.QMessageBox()
        msg.setIcon(QtWidgets.QMessageBox.Critical)
        msg.setText("Erro, não consegui achar o arquivo :(\n")

```
falar que nao tem como ter polimorfismo parametrico pela falta de tipagem em python como CURIOSIDADE. 


## Link para o repositório
<a> https://github.com/peusucodemanga/SisuUFS-POO </a>



<!-- --

Neste documento deve constar:

1. Identificação dos membros da equipe
2. Link para o repositório
3. Descrição do tema do trabalho
4. Discução do que conseguiram desenvolver
5. Discução da OO na segunda linguagem adotada

topico quentes(hot pockets)dicas quentissimas(hot dicks)
herança nas classes com object do ladinho
herança nas classes com QMainWindow ou algo assim

Manejamento de arquivos diferentes usando os imports e as diferentes classes
