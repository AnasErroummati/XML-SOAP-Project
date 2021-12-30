from zeep import Client

filebrowser = Client('app/src/main/resources/FileBrowserService.wsdl').service

#Test: 1- Browse into ServerShare/Dir/dir2 then go back to ServerShare/Dir/ 
#      2- Rename the AZ.txt file into Text.txt
#      3-delete ServerShare/RTE.txt
#      4- Download the file ServerShare/Text.txt
#      5- Upload the Test.txt
while(1):
    print("\n")
    print("1- Browse a file")
    print("2- Rename a file")
    print("3- Delete a file")
    print("4- Download a file")
    print("5- Upload a file")
    print("6- Quit")
    print("\n")
    Val= input("Enter Your Choice:  ")

    if(Val== "1"):
        print("\n")
        T= input("Choose a folder (Enter a space "" for Main Folder is ServerShare) || enter ./ to go to parent Folder \n")
        files=filebrowser.Browse(T)
        for i in range(len(files)):
            print(files[i])
        
    elif(Val=="2"):
        T=input("Enter the name of the file you want to rename   ")
        T2=input("Enter the New name of the file   ")
        r= filebrowser.Rename(T,T2)
        if(r):
            print("Renaming Done succesfully")
        else:
             print("File not in the current folder")
    elif(Val=="3"):
        T=input("Enter the name of the file you want to delete  ")
        r= filebrowser.delete(T)
        if(r):
            print("delation Done succesfully")
        else:
           print("File not in the current folder")

    elif(Val=="4"):
        T=input("Enter the name of the file you want to Download  ")
        T1= input("Enter the path where you want to save the file  ")
        r= filebrowser.download(T)
        with open("app/src/main/Python/"+ T1, "wb") as f:
            f.write(r)
            t=True
        f.close()

        if(t):
            print("Download Done succesfully")
        else:
           print("File not in the current folder")


    elif(Val=="5"):
            T= input("Enter the path of the file to upload  ")
            T1=input("Enter where you want to Upload the file  ")
            in_file = open("app/src/main/Python/"+T, "rb") 
            data = in_file.read()
            in_file.close()
            h = filebrowser.upload(data,T1)
            if(h):
                print("file uploaded successfully")
            else:
                print("file not found")
    
    elif(Val=="6"):
        print("Quitting.....")
        break
    else:
        print("Enter a valid choice")
        break 