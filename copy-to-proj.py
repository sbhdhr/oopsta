# -*- coding: utf-8 -*-
"""
Created on Thu Nov 12 23:00:00 2020

@author: Subhashis
"""

import fileinput
import glob
import shutil
import os

source_dir='D:\\oopstudfiles\\extractedfiles'

dest_dir='C:\\Users\\Subhashis\\eclipse-workspace\\oopslab\\src\\main\\java\\oopslab'


i=0

java_files = glob.glob(source_dir+'\\*.java')

#print(java_files)

for filename in java_files:
    i=i+1
    with fileinput.FileInput(filename, inplace=True, backup='.bak') as file:
        for line in file:
            #line = line.replace('class Student', 'class Student'+str(i))
            line = line.replace('Student', 'Student'+str(i))
            line = line.replace('Aggregate', 'Aggregate'+str(i))
            line = line.replace('Student(String fName', 'Student'+str(i)+'(String fName')
            #line = line.replace('interface Aggregate', 'interface Aggregate'+str(i))
            line = line.replace('QP2_Student'+str(i)+'_ID_number.txt', 'QP2_Student_ID_number.txt')
            line = line.replace('public class OnlineTestQP2', 'public class '+
                                os.path.basename(filename).replace('.java', ''))
            print(line.replace('package ', 'package oopslab;\n'), end='')
            
java_files = glob.glob(source_dir+'\\*.java')

# file1 = open("testcode.txt","w")

for filename in java_files: 
    print('Copied ',filename)         
    shutil.move(filename, dest_dir+'\\'+os.path.basename(filename))
    # file1.write('Map<String, Student> hm = '
    #             +os.path.basename(filename).replace('.java', '')
    #             +'.populateMap();\n')
    # file1.write('hashMaps.add(hm);\n')

# file1.close()      