import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
import csv

janela = tk.Tk()
janela.title("Dados Sisu UFS")
janela.maxsize(1000,800)
janela.minsize(600, 400)

label = tk.Label(janela, text = "Exibicao do CSV dos dados do SiSU")
label.pack()

def arquivo():
    arquivo = entrada.get()
    try:
        with open(arquivo, 'r', newline='') as file:
            csv_reader = csv.reader(file)
            Cabecalho = next(csv_reader)  
            tree.delete(*tree.get_children()) 

            tree["columns"] = Cabecalho

            for col in Cabecalho:
                tree.heading(col, text=col)
                tree.column(col, width=50)

            for row in csv_reader:
                tree.insert("", "end", values=row)

            status_label.config(text=f"Arquivo lido com sucesso!\n Local escolhido \"{arquivo}\" ")

            # label["text"]= f"Esse foi o texto digitado:{df}"
    except FileNotFoundError:
        messagebox.showerror("Erro", f"Não consegui achar :(\n")

tree = ttk.Treeview(janela, show="headings")
tree.pack(padx=20, pady=20, fill="both", expand=True)    

status_label = tk.Label(janela, text="", padx=20, pady=10)
status_label.pack()

entrada = tk.Entry(janela)
entrada.pack(side="bottom",pady = 10)

botao = tk.Button(janela, text="Escolha o local do CSV", command = arquivo)
botao.pack()

janela.mainloop()