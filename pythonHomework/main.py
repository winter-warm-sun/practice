import os.path  # 导入os.path模块


def menm():  # 菜单
    print('==========================PDF文档操作管理系统==========================')
    print('------------------------------功能菜单-----------------------------')
    print('\t\t\t   1.提取PDF文本')
    print('\t\t\t   2.提取PDF表格')
    print('\t\t\t   3.PDF表格导入Excel')
    print('\t\t\t   4.提取PDF图片')
    print('\t\t\t   5.合并多个PDF')
    print('\t\t\t   6.拆分PDF')
    print('\t\t\t   7.word转PDF')
    print('\t\t\t   8.PDF转word')
    print('\t\t\t   0.退出系统')
    print('-------------------------------------------------------------------')


def main():  # 主程序
    while True:
        menm()  # 调用菜单函数
        choice = int(input('请选择：'))
        if choice in [0, 1, 2, 3, 4, 5, 6, 7, 8]:
            if choice == 0:
                answer = input('您确定要退出系统吗？y/n   ')
                if answer == 'y' or answer == 'Y':
                    print('谢谢您的使用！！!')
                    break  # 退出系统
                else:
                    continue
            elif choice == 1:
                path = input('请输入要提取文本PDF的路径：')
                read_text(path)  # 提取PDF文本
            elif choice == 2:
                path = input('请输入要提取表格PDF的路径：')
                read_excel(path)  # 提取PDF表格
            elif choice == 3:
                path1 = input('请输入要导入excel表格的PDF路径：')
                path2 = input('请输入excel路径：')
                import_excel(path1, path2)  # PDF表格导入Excel
            elif choice == 4:
                path = input('请输入要提取图片的PDF路径：')
                read_img(path)  # 提取PDF图片
            elif choice == 5:
                path1 = input('请输入要合并的PDF路径1：')
                path2 = input('请输入要合并的PDF路径2：')
                merge_pdf(path1, path2)  # 合并多个PDF
            elif choice == 6:
                path1 = input('请输入要拆分的PDF路径：')
                path2 =input('请输入要存放的路径:')
                split_pdf(path1, path2)  # 拆分PDF
            elif choice == 7:
                path = input('请输入要word转PDF路径：')
                word_pdf(path)  # word转PDF
            elif choice == 8:
                path = input('请输入要PDF转word路径：')
                pdf_word(path)  # PDF转word

def read_text(path):
    import pdfplumber
    with pdfplumber.open(path) as pdf:

        for page in pdf.pages:
            print(page.extract_text())

def read_excel(path):
    import pdfplumber
    import pandas as pd
    with pdfplumber.open(path) as pdf:
        for pages in pdf.pages:
            for table in pages.extract_tables():
                data = pd.DataFrame(table[1:], columns=table[0])
                print(data)

def import_excel(path1,path2):
    import pdfplumber
    import pandas as pd
    count = 1
    with pdfplumber.open(path1) as pdf:
        with pd.ExcelWriter(path2) as writer:
            for pages in pdf.pages:
                for table in pages.extract_tables():
                    data = pd.DataFrame(table[1:], columns=table[0])
                    data.to_excel(writer, sheet_name=f'sheet{count}')  # 分列表导出excel
                    count += 1
# def read_img(path):
#
# def merge_pdf(path1,path2):
#
def split_pdf(path1,path2):
    from PyPDF2 import PdfFileReader, PdfFileWriter  # 读和写

    read = PdfFileReader(path1)
    for page in range(read.getNumPages()):  # getNumPages()获取总页数
        write = PdfFileWriter()  # 实例化对象
        write.addPage(read.getPage(page))  # 将遍历出的每一页添加到实例化对象中
        with open(path2+f'/{page + 1}.pdf', "wb") as name:
            write.write(name)


# def word_pdf(path):
#
# def pdf_word(path):

if __name__ == '__main__':
    main()