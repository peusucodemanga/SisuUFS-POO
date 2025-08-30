from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtChart import QChart, QChartView, QLineSeries


class Ui_GraficoColunas(object):
    def setupUi(self, GraficoColunas):
        GraficoColunas.setObjectName("GraficoColunas")
        GraficoColunas.resize(760, 520)
        GraficoColunas.setMinimumSize(QtCore.QSize(760, 520))
        self.centralwidget = QtWidgets.QWidget(GraficoColunas)
        self.centralwidget.setObjectName("centralwidget")
        self.chart = QChart()
        self.series = QLineSeries()
        for i in range(10):
            self.series.append(i, i*10)
        self.chart.addSeries(self.series)
        self.chartView = QChartView(self.chart)
        GraficoColunas.setCentralWidget(self.chartView)
        


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    GraficoColunas = QtWidgets.QMainWindow()
    ui = Ui_GraficoColunas()
    ui.setupUi(GraficoColunas)
    GraficoColunas.show()
    sys.exit(app.exec_())
