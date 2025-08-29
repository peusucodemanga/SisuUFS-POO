from PyQt5 import QtCore, QtGui, QtWidgets

class MyTableWidget(QtWidgets.QTableWidget):
    def __init__(self,params) -> None:
        super().__init__(params)
        self.setEditTriggers(QtWidgets.QAbstractItemView.NoEditTriggers)