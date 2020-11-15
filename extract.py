# -*- coding: utf-8 -*-
"""
Created on Fri Nov 13 20:02:08 2020

@author: Subhashis
"""

from zipfile import ZipFile 
import glob
import os
from rarfile import RarFile
import rarfile
import shutil

rarfile.UNRAR_TOOL="C:\\Program Files\\WinRAR\\UnRAR.exe"

source_dir='D:\\oopstudfiles\\zips'

dest_dir='D:\\oopstudfiles\\extractedfiles'


files = [glob.glob(e) for e in [source_dir+'\\*.rar', source_dir+'\\*.zip']]

# files[0] - rar
# files[1] - zip

# print('Zip files: ',len(files[1]))

# i=0
# for file in files[1]:
#     flag=True
#     try:
#         with ZipFile(file) as z:
#             for zfile in z.namelist():
#                 if 'QP2_' in zfile or 'Q2' in zfile:
#                     newname = zfile[zfile.find('/')+1:]
#                     #print('newname:'+newname)
#                     with z.open(zfile) as zf, open(dest_dir+'\\'+newname, 'wb') as f:
#                         shutil.copyfileobj(zf,f)
#                     i=i+1
#                     flag=False
#                     #print(os.path.basename(file)+" :: "+zfile)
#     except Exception as e:
#         print(os.path.basename(file)+" :: "+str(e))
#         flag=False
        
#     if flag:
#         print(os.path.basename(file)+" :: error")
#         # with ZipFile(file) as z:
#         #     print(z.namelist())
        
        
# print('zips processed successfully: ',i)

print('Rar files: ',len(files[1]))

i=0
for file in files[0]:
    flag=True
    try:
        with RarFile(file) as z:
            for zfile in z.namelist():
                if 'QP2_' in zfile or 'Q2' in zfile:
                    newname = zfile[zfile.find('/')+1:]
                    #print('newname:'+newname)
                    with z.open(zfile) as zf, open(dest_dir+'\\'+newname, 'wb') as f:
                        shutil.copyfileobj(zf,f)
                    i=i+1
                    flag=False
                    #print(os.path.basename(file)+" :: "+zfile)
    except Exception as e:
        print(os.path.basename(file)+" :: "+str(e))
        flag=False
        
    if flag:
        print(os.path.basename(file)+" :: error")
        with RarFile(file) as z:
            print(z.namelist())
        
        
print('Rars processed successfully: ',i)

    