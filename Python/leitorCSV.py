import sys
import csv
from PyQt5 import QtWidgets, QtGui, QtChart
from PyQt5.QtGui import QFont,QColor
from PyQt5.QtCore import pyqtSignal
from JanelaPrincipal import Ui_MainWindow
from JanelaSecundaria import Ui_JanelaSecundaria
from GraficoColuna import Ui_GraficoColunas
from GraficoSetor import Ui_GraficoSetores

aprovados = []

class GraficoSetor(QtWidgets.QMainWindow, Ui_GraficoSetores):
    def __init__(self, *args, obj=None, **kwargs):
        super(GraficoSetor, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle("Gráfico de Setores")

class GraficoColunas(QtWidgets.QMainWindow, Ui_GraficoColunas):
    def __init__(self, *args, obj=None, **kwargs):
        super(GraficoColunas, self).__init__(*args, **kwargs)
        self.setupUi(self)
        self.setWindowTitle("Gráfico de Colunas")

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

    def graficoSetores(self):
        self.window = GraficoSetor()
        self.centrosDict = {
            #Colocando todos os centros
            "CCAA":0,
            "CCBS":0,
            "CCET":0,
            "CCSA":0,
            "CECH":0,
            #Colocando todos os campus
            "CAMPUSLAG":0,
            "CAMPUSLAR":0,
            "CAMPUSSER":0,
            "CAMPUSITA":0,
        }

        self.CCAA = ["ZOOTECNIA BAC (MATUTINO)","MEDICINA VETERINARIA(INTEGRAL)","ENGENHARIA FLORESTAL (MAT)","ENGENHARIA DE PESCA (VESP)","ENGENHARIA AGRONOMICA (MAT)","ENGENHARIA AGRICOLA (MATUTINO)"]
        self.CCBS = ["ODONTOLOGIA (INTEGRAL)","NUTRICAO - BAC (INTEGRAL)","MEDICINA (INTEGRAL)","FONOAUDIOLOGIA - BAC (MAT)","FISIOTERAPIA - BAC (MATUTINO)","FISICA MEDICA BAC (VESPERTINO)","FARMACIA (VESPERTINO)","ENFERMAGEM - BAC (INTEGRAL)","EDUCACAO FISICA - LIC (VESP)","EDUCACAO FISICA - BAC (MAT)","ECOLOGIA - BAC. (MATUTINO)","C. BIOLOGICAS - LIC (VESP)","C. BIOLOGICAS - LIC (NOTURNO)","C. BIOLOGICAS - BAC (MATUTINO)"]
        self.CCET = ["SISTEMAS DE INFORMACAO (NOT)","QUIMICA LIC (NOTURNO)","QUIMICA INDUSTRIAL (MATUTINO)","QUIMICA BAC (VESPERTINO)","MATEMATICA APLICADA BAC (VESP)","MATEMATICA BAC (VESPERTINO)","MATEMATICA LIC (NOTURNO)","MATEMATICA LIC (VESPERTINO)","FISICA-BAC HAB ASTRONOMIA(VES)","FISICA LIC (NOTURNO)","FISICA BAC (VESPERTINO)","ESTATISTICA BAC (NOTURNO)","ENGENHARIA QUIMICA (MATUTINO)","ENGENHARIA MECANICA (MATUTINO)","ENGENHARIA ELETRONICA (MAT)","ENGENHARIA ELETRICA (MATUTINO)","ENGENHARIA DE PRODUCAO (VES)","ENGENHARIA DE MATERIAIS (VESP)","ENGENHARIA DE PETROLEO (MAT)","ENGENHARIA DE COMPUTACAO (VES)","ENGENHARIA DE ALIMENTOS (MAT)","ENGENHARIA CIVIL (VESPERTINO)","ENGENHARIA AMBIENTAL(MATUTINO)","CIENCIAS ATUARIAIS - BAC (NOT)","C. DA COMPUTACAO BAC (VESP)"]
        self.CCSA = ["TURISMO - BAC (VESPERTINO)","SERVICO SOCIAL BAC (NOTURNO)","SECRETARIADO EXECUTIVO (NOT)","REL. INTERNACIONAIS-BAC(VESP)","DIREITO BAC (VESPERTINO)","DIREITO BAC (NOTURNO)","CIENCIAS ECONOMICAS BAC(VESP)","CIENCIAS ECONOMICAS BAC (NOT","CIENCIAS CONTABEIS BAC (NOT)","BIBLIOTECONOMIA E DOC-BAC(NOT)","ADMINISTRACAO BAC (NOTURNO)","ADMINISTRACAO BAC (VESPERTINO)"]
        self.CECH = ["TEATRO - LIC (NOTURNO)","PUBLICIDADE E PROPAGANDA (VES)","PSICOLOGIA BAC (VESPERTINO)","PEDAGOGIA LIC (VESPERTINO)","PEDAGOGIA LIC (NOTURNO)","LETRAS ESPANHOL LIC (NOTURNO)","LETRAS INGLES LIC (NOTURNO)","LETRAS PORT-ESPANHOL-LIC(VESP)","LETRAS PORT-FRANCES LIC (NOT)","LETRAS PORT-INGLES LIC (MAT)","LETRAS PORTUGUES LIC (NOTURNO)","LETRAS PORTUGUES LIC(MATUTINO)","JORNALISMO BAC (MATUTINO)","HISTORIA LIC (MATUTINO)","HISTORIA LIC (NOTURNO)","GEOLOGIA - BAC (MATUTINO)","GEOGRAFIA LIC (MATUTINO)","GEOGRAFIA - LIC (NOTURNO)","GEOGRAFIA - BAC (MATUTINO)","FILOSOFIA LIC (NOTURNO)","DESIGN - BAC. (NOTURNO)","CINEMA E AUDIOVISUAL BAC(VESP)","CIENCIAS SOCIAIS BAC (VESP)","CIENCIAS DA RELIGIAO LIC (NOT)","ARTES-LIC EM A. VISUAIS (VESP)"]
        self.CAMPUSLAG = ["ODONTOLOGIA - BAC (INTEGRAL)","NUTRICAO - BAC (INTEGRAL)","MEDICINA - BAC (INTEGRAL)","FONOAUDIOLOGIA-BAC (INTEGRAL)","FISIOTERAPIA - BAC (INTEGRAL)","FARMACIA - BAC (INTEGRAL)","ENFERMAGEM - BAC (INTEGRAL)"]
        self.CAMPUSLAR = ["TERAPIA OCUPACIONAL-BAC (INT)","MUSEOLOGIA - BAC (MATUTINO)","DANCA - LIC (MATUTINO)","ARQUITETURA E URBANISMO (INT)","ARQUEOLOGIA - BAC (VESPERTINO)"]
        self.CAMPUSSER = ["ZOOTECNIA - BACHARELADO (INT)","MEDICINA VETERINARIA-BAC (INT)","ENGENHARIA AGRONOMICA-BAC(INT)","AGROINDUSTRIA - BAC (INT)"]
        self.CAMPUSITA = ["SISTEMAS DE INFORMACAO (MAT)","QUIMICA - LICENCIATURA (MAT)","PEDAGOGIA - LIC (NOTURNO)","MATEMATICA-LICENCIATURA (VESP)","LETRAS PORTUGUES - LIC (NOT)","GEOGRAFIA - LICENCIATURA(VESP)","FISICA - LICENCIATURA (NOT)","C. BIOLOGICAS - LIC (VESP)","ADMINISTRACAO - BAC (NOTURNO)"]

        for candidato in aprovados :
            curso = candidato["Curso"]
            if(curso in self.CCAA):
                contagemAtual = self.centrosDict.get("CCAA")
                self.centrosDict.update({"CCAA": 1+contagemAtual})

            if(curso in self.CCBS):
                contagemAtual = self.centrosDict.get("CCBS")
                self.centrosDict.update({"CCBS": 1+contagemAtual})
                
            if(curso in self.CCET):
                contagemAtual = self.centrosDict.get("CCET")
                self.centrosDict.update({"CCET": 1+contagemAtual})
            
            if(curso in self.CCSA):
                contagemAtual = self.centrosDict.get("CCSA")
                self.centrosDict.update({"CCSA": 1+contagemAtual})
            
            if(curso in self.CECH):
                contagemAtual = self.centrosDict.get("CECH")
                self.centrosDict.update({"CECH": 1+contagemAtual})
            
            if(curso in self.CAMPUSLAG):
                contagemAtual = self.centrosDict.get("CAMPUSLAG")
                self.centrosDict.update({"CAMPUSLAG": 1+contagemAtual})
            
            if(curso in self.CAMPUSLAR):
                contagemAtual = self.centrosDict.get("CAMPUSLAR")
                self.centrosDict.update({"CAMPUSLAR": 1+contagemAtual})
            
            if(curso in self.CAMPUSSER):
                contagemAtual = self.centrosDict.get("CAMPUSSER")
                self.centrosDict.update({"CAMPUSSER": 1+contagemAtual})
            
            if(curso in self.CAMPUSITA):
                contagemAtual = self.centrosDict.get("CAMPUSITA")
                self.centrosDict.update({"CAMPUSITA": 1+contagemAtual})

        #plotando grafico 
        cores = ["#000000","#25083A","#2C0E37","#690375","#CB429F","#D053A8","#D463B0","#D871B7","#DC7EBE"]
        # :D colocando cor no grafico
        i=0
        for centro in self.centrosDict:
            self.pedaco = QtChart.QPieSlice(centro,self.centrosDict.get(centro))
            self.window.series.append(self.pedaco)
            self.pedaco.setLabel(f"{self.centrosDict.get(centro)}")
            self.window.chart.legend().markers(self.window.series)[i].setLabel(centro)
            self.pedaco.setColor(QColor(cores[i]))
            self.pedaco.setLabelFont(QFont("Arial", 12, QFont.Bold))
            self.pedaco.setLabelColor(QColor("white"))
            i+=1
            
        
        self.window.series.setLabelsVisible(True)
        self.window.series.setLabelsPosition(QtChart.QPieSlice.LabelInsideHorizontal)
        self.window.show()

        
class MainWindow(QtWidgets.QMainWindow, Ui_MainWindow):
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
                if (".csv" not in arquivo):
                    raise IOError("O arquivo não respeita o formato")
                Cabecalho = next(leitorCSV)

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
            msg.setText("Erro, não consegui achar o arquivo " + arquivo + " :(\n")
            msg.setInformativeText("Tente verificar os diretórios do arquivo e digitar novamente!")
            msg.setWindowTitle("Erro!")
            msg.exec_()
        except IOError:
            msg = QtWidgets.QMessageBox()
            msg.setIcon(QtWidgets.QMessageBox.Critical)
            msg.setText("Erro! O arquivo " + arquivo + "\nnão respeita o formato exigido!\n")
            msg.setInformativeText("Tente digitar novamente o nome do arquivo!")
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
