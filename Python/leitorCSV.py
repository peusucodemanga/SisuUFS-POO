import sys
from PyQt5 import QtWidgets, QtGui
from PyQt5.QtCore import pyqtSignal
import csv
from JanelaPrincipal import Ui_MainWindow
from JanelaSecundaria import Ui_JanelaSecundaria
from GraficoColuna import Ui_GraficoColunas

class JanelaSecundaria(QtWidgets.QMainWindow, Ui_JanelaSecundaria):
    def __init__(self, *args, obj=None, **kwargs):
        super(JanelaSecundaria, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle("Pagina de opções")
        #Definindo os campus que podem ser filtrados
        listaCampus = []
        for dic in aprovados:
            listaCampus.append(dic["Campus"])
        listaCampus = list(set(listaCampus)) #eliminando os repitidos
        listaCampus.append("TODOS")
        for campus in listaCampus:
            self.comboBox.addItem(campus)
        self.comboBox.setCurrentIndex(listaCampus.index("TODOS")) #definindo por padrao o todos, ou seja, sem nenhum filtro

    def acharMaxMin(self):
        maiorNota=(max(aprovados, key=lambda a: float(a["Nota"])))
        menorNota=(min(aprovados, key=lambda a: float(a["Nota"])))
        Nota=f"A maior nota foi:{maiorNota["Nota"]} Curso: {maiorNota["Curso"]} Campus: {maiorNota["Campus"]}\n A menor nota foi:{menorNota["Nota"]} Curso: {menorNota["Curso"]} Campus:{menorNota["Campus"]}\n"
        self.LabelNotas.setText(Nota)

    def graficoColunas(self):
            self.window = GraficoColunas()
            #Daqui pra baixo eh coletando os dados necessarios para o grafico
            self.qntCandidatos = [0] * 9
            self.notas = []
            for i in range(10):
                if(i == 9):
                    nota = 400 + 50*(i+1)
                else: nota = 400 + 50 * i
                self.notas.append(str(nota))

            self.intervalos = []
            for i in range(9):
                aux = self.notas[i] + " - " + self.notas[i+1]
                self.intervalos.append(aux)
            

            if(self.comboBox.currentText() != 'TODOS'):
                for aprovado in aprovados:
                    print(aprovado)
                    if(aprovado['Campus'] == self.comboBox.currentText()):
                        index = (int(float(aprovado['Nota'])/50)) - 8
                        if(index >= 9): index = 8
                        self.qntCandidatos[index] += 1
            else:
                 for aprovado in aprovados: 
                        index = (int(float(aprovado['Nota'])/50)) - 8
                        if(index >= 9): index = 8
                        self.qntCandidatos[index] += 1                       

            self.dados = dict(zip(self.intervalos,self.qntCandidatos))
            #Daqui pra baixo eh o setup do grafico
            self.window.grafico.append(self.dados.values())
            self.window.series1.append(self.window.grafico)
            self.window.chart.addSeries(self.window.series1)

            self.window.eixoX.append(self.intervalos)
            #self.window.chart.setAxisX(self.window.eixoX,self.window.series1) #morreu aqui
            # self.window.chart.setAxisY(self.window.eixoY,self.window.series1) # nem testei
            valorMax = max(self.dados.values())
            self.window.eixoY.setRange(0,valorMax + 0.05*valorMax)        
                    
            self.window.show()

class GraficoColunas(QtWidgets.QMainWindow, Ui_GraficoColunas):
    def __init__(self, *args, obj=None, **kwargs):
            super(GraficoColunas, self).__init__(*args, **kwargs)
            self.setupUi(self)
            self.setWindowTitle("Gráfico de Colunas")
            
    

class MainWindow(QtWidgets.QMainWindow, Ui_MainWindow):
    dados = pyqtSignal(list)

    def __init__(self, *args, obj=None, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle("Leitor de CSV 8000")

    def abrirTlSecundaria(self):
        self.window = JanelaSecundaria()
        self.window.show()

    def lerArquivo(self):
        arquivo = self.entrada.text()
        try:
            with open(arquivo, 'r', newline='') as file:
                leitorCSV = csv.reader(file)
                Cabecalho = next(leitorCSV)
                global aprovados 
                aprovados = []

                for linha in leitorCSV:
                    aprovado=dict(zip(Cabecalho,linha))
                    aprovados.append(aprovado)
                qntLinhas = len(aprovados)

                self.tableWidget.setColumnCount(len(Cabecalho))
                self.tableWidget.setRowCount(qntLinhas)
                self.tableWidget.setHorizontalHeaderLabels(Cabecalho)                
                i = 0
                while(i < len(Cabecalho)):
                    self.tableWidget.setColumnWidth(i, 250) 
                    i+= 1

                i = 0
                for aprovado in aprovados:
                    j = 0
                    for dadoLinha in aprovado.values():
                        item = QtWidgets.QTableWidgetItem(str(dadoLinha))
                        self.tableWidget.setItem(i, j, item)
                        j += 1
                    i += 1
    
                self.abrirTlSecundaria()

        except FileNotFoundError:
            msg = QtWidgets.QMessageBox()
            msg.setIcon(QtWidgets.QMessageBox.Critical)
            msg.setText("Erro, não consegui achar o arquivo :(\n")
            msg.setInformativeText("Tente verificar os diretórios do arquivo e digitar novamente!")
            msg.setWindowTitle("Erro!")
            msg.exec_()

    def slotLerCSV(self):
        self.lerArquivo()
        
        
def main():            
    app = QtWidgets.QApplication(sys.argv)

    window = MainWindow()
    window.show()
    app.exec()

if __name__ == '__main__':
    main()
