from PyQt5 import QtCore, QtGui, QtWidgets,QtChart


class Ui_GraficoSetores(object):
    def setupUi(self, GraficoSetores):
        GraficoSetores.setObjectName("GraficoSetores")
        GraficoSetores.resize(1000,600)
        GraficoSetores.setMinimumSize(QtCore.QSize(760, 520))
        GraficoSetores.setWindowTitle("Gráfico de setores da quantidade de aprovados por centro/campus")
        self.centralwidget = QtWidgets.QWidget(GraficoSetores)
        self.centralwidget.setObjectName("centralwidget")

        #Grafico
        self.series = QtChart.QPieSeries()
        self.chart = QtChart.QChart()
        self.chart.addSeries(self.series)
        self.chart.setTitle("Grafico de setores") 
        self.chart.legend().setAlignment(QtCore.Qt.AlignBottom)
        self.chart.legend().setVisible(True)
        self.chartView = QtChart.QChartView(self.chart)
        self.chartView.setRenderHint(QtGui.QPainter.Antialiasing)
        GraficoSetores.setCentralWidget(self.chartView)


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    GraficoSetores = QtWidgets.QMainWindow()
    ui = Ui_GraficoSetores()
    ui.setupUi(GraficoSetores)
    GraficoSetores.show()
    sys.exit(app.exec_())