CXX = g++
CXXFLAGS = -Wall -g

all_old: Analyzer.o Main.o
	$(CXX) $(CXXFLAGS) -std=c++11 -o main Analyzer.o Main.o

all: Analyzer.o Main.o
	g++ -L/home1/j/josepma/CIT595_HW5/boost_1_60_0/ -lboost_filesystem Analyzer.o Main.o -o main

Main.o: Main.cpp
	$(CXX) -std=c++11 -c Main.cpp

Analyzer.o: Analyzer.cpp
	$(CXX) -c Analyzer.cpp

clean:
	rm main *.o
