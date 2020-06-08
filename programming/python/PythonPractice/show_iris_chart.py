from PIL import Image

img = Image.open('iris_pie_for_one_variable.png')
img.show()

img2 = Image.open('iris_pie_for_each_variable.png')
img2.show()

img3 = Image.open('iris_bar_for_one_variable.png')
img3.show()

img4 = Image.open('iris_bar_for_each_variable.png')
img4.show()

img5 = Image.open('iris_histograms.png')
img5.show()

img6 = Image.open('iris_hist_by_spec.png')
img6.show()

img7 = Image.open('boxplot.png')
img7.show()

img8 = Image.open('scatterplot.png')
img8.show()

img9 = Image.open('scatter_w_color.png')
img9.show()
