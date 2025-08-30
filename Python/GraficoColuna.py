from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtChart import QBarSeries,QBarSet,QChart, QChartView, QLineSeries, QBarCategoryAxis, QValueAxis


class Ui_GraficoColunas(object):
    def setupUi(self, GraficoColunas):
        GraficoColunas.setObjectName("GraficoColunas")
        GraficoColunas.resize(760, 520)
        GraficoColunas.setMinimumSize(QtCore.QSize(760, 520))
        GraficoColunas.setWindowTitle("Gráfico de aprovados por campus")
        self.centralwidget = QtWidgets.QWidget(GraficoColunas)
        self.centralwidget.setObjectName("centralwidget")
        #Editar daqui pra baixo, pra mudar o tipo do grafico
        
        self.grafico = QBarSet("Quantidade de candidatos")

        self.series1 = QBarSeries()

        self.chart = QChart()
        self.chart.addSeries(self.series1)
        self.chart.setTitle("Distribuição de aprovados por intervalo")

        self.eixoX = QBarCategoryAxis()
        self.chart.setAxisX(self.eixoX,self.series1)

        self.eixoY = QValueAxis()
        self.chart.setAxisY(self.eixoY,self.series1)

        self.chart.legend().setVisible(True)
        self.chart.legend().setAlignment(QtCore.Qt.AlignBottom)
        self.chart.axisX().setTitleText("Intervalos de nota")
        self.chart.axisY().setTitleText("Quantidade de candidatos")
        self.chart.setAnimationOptions(QChart.SeriesAnimations)

        self.chartView = QChartView(self.chart)
        self.chartView.setRenderHint(QtGui.QPainter.Antialiasing)
        GraficoColunas.setCentralWidget(self.chartView)
        


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    GraficoColunas = QtWidgets.QMainWindow()
    ui = Ui_GraficoColunas()
    ui.setupUi(GraficoColunas)
    GraficoColunas.show()
    sys.exit(app.exec_())
