import sys
from PyQt5 import QtWidgets, QtGui
import csv
from JanelaPrincipal import Ui_MainWindow
from JanelaSecundaria import Ui_JanelaSecundaria

class MainWindow(QtWidgets.QMainWindow, Ui_MainWindow):
    aprovados = []

    def __init__(self, *args, obj=None, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle("Leitor de CSV 8000")


    def abrirTlSecundaria(self):
        self.window = QtWidgets.QMainWindow()
        self.ui = Ui_JanelaSecundaria()
        self.ui.setupUi(self.window)
        self.window.show()


    def lerArquivo(self):
        arquivo = self.entrada.text()
        try:
            with open(arquivo, 'r', newline='') as file:
                leitorCSV = csv.reader(file)
                Cabecalho = next(leitorCSV)
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