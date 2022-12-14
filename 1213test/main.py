from PyPDF2 import PdfFileReader, PdfFileWriter  # 读和写

path = './表格.pdf'
path2 ='D:/GitHub/1213test'
read = PdfFileReader(path)
for page in range(read.getNumPages()):  # getNumPages()获取总页数
    write = PdfFileWriter()  # 实例化对象
    write.addPage(read.getPage(page))  # 将遍历出的每一页添加到实例化对象中
    with open(path2+f'/{page + 1}.pdf', "wb") as name:
        write.write(name)
