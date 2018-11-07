import csv

def main():
	fin = "intro.csv"
	fout = "delete.csv"
	PreProcessCSV(fin, fout)
	print("file success processed!")


#删除csv最后一列
def PreProcessCSV(finName, foutName):
	fin = open(finName, "r")
	fout = open(foutName, "w")
	writer = csv.writer(fout)
	for row in csv.reader(fin):
		writer.writerow(row[:-1])


main()

