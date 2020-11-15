# -*- coding: utf-8 -*-
"""
Created on Thu Nov 12 23:23:44 2020

@author: Subhashis
"""
import os
import glob

source_dir='D:\\oopstudfiles\\extractedfiles'


java_files = glob.glob(source_dir+'\\*.java.bak')

for file in java_files:
    #os.remove(file)
    name = os.path.basename(file).replace('.bak', '')
    print(name)
    os.rename(file,source_dir+'\\'+name)