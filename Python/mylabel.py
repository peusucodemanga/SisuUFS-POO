from PyQt5 import QtWidgets

class MyLabel(QtWidgets.QLabel):
    def __init__(self,params) -> None:
        super().__init__(params)