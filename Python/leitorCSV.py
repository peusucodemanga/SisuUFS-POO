import sys
from PyQt5 import QtWidgets, QtGui
from PyQt5.QtCore import pyqtSignal
import csv
from JanelaPrincipal import Ui_MainWindow
from JanelaSecundaria import Ui_JanelaSecundaria

class JanelaSecundaria(QtWidgets.QMainWindow, Ui_JanelaSecundaria):
    def __init__(self, *args, obj=None, **kwargs):
        super(JanelaSecundaria, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle("Pagina de opcoes")

    def acharMaxMin(self):
        maiorNota=(max(aprovados, key=lambda a: float(a["Nota"])))
        menorNota=(min(aprovados, key=lambda a: float(a["Nota"])))
        Nota=f"A maior nota foi:{maiorNota["Nota"]} Curso: {maiorNota["Curso"]} Campus: {maiorNota["Campus"]}\n A menor nota foi:{menorNota["Nota"]} Curso: {menorNota["Curso"]} Campus:{menorNota["Campus"]}\n"
        self.LabelNotas.setText(Nota)
    
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
