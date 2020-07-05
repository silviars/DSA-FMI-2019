package hw7.FileSystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FileSystems {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        FileSystems fileSystems = new FileSystems();

        int requests = Integer.parseInt(reader.readLine());
        String DIR_NAME;
        for(int i = 0; i < requests; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            String command = tokenizer.nextToken();
            switch(command){
                case "mkdir":
                    DIR_NAME = tokenizer.nextToken();
                    fileSystems.mkdir(DIR_NAME);
                    break;
                case "cd":
                    DIR_NAME = tokenizer.nextToken();
                    fileSystems.cd(DIR_NAME);
                    break;
                case "pwd":
                    fileSystems.pwd();
                    break;
                case "ls":
                    fileSystems.ls();
                    break;
            }
        }

        reader.close();
    }

    private Directory root;
    private Directory currentDirectory;

    public FileSystems(){
        this.root = new Directory("/");
        this.currentDirectory = root;
    }

    public void mkdir(String DIR_NAME){
        if(containsDirectory(DIR_NAME)){
            System.out.print("Directory already exists\n");
        } else {
            Directory newDirectory = new Directory(DIR_NAME);
            newDirectory.parent = currentDirectory;
            currentDirectory.subDirectories.add(newDirectory);
        }
    }

    public void cd(String DIR_NAME){
        if(containsDirectory(DIR_NAME)){
            currentDirectory = getDirectory(DIR_NAME);
        } else if(DIR_NAME.equals("..")){
            if(currentDirectory != root) {
                currentDirectory = currentDirectory.parent;
            } else {
                System.out.print("No such directory\n");
            }
        } else {
            System.out.print("No such directory\n");
        }
    }
    public void pwd(){
        Directory currDir = currentDirectory;
        StringBuilder absolutePath = new StringBuilder();
        if(currDir == root){
            System.out.print("/\n");
        } else {
            while (currDir != root) {
                absolutePath.insert(0, currDir.name + "/");
                currDir = currDir.parent;
            }
            absolutePath.insert(0, "/");
            System.out.print(absolutePath.toString() + "\n");
        }
    }

    public void ls(){
        String[] currentDirChildren = getChildren();
        Arrays.sort(currentDirChildren);
        StringBuilder subDirectories = new StringBuilder();
        for(String currChild: currentDirChildren){
            subDirectories.append(currChild + " ");
        }
        System.out.print(subDirectories.toString() + "\n");
    }

    private boolean containsDirectory(String DIR_NAME){
        for(Directory directory: currentDirectory.subDirectories){
            if(directory.name.equals(DIR_NAME))
                return true;
        }
        return false;
    }

    private Directory getDirectory(String DIR_NAME){
        for(Directory directory: currentDirectory.subDirectories){
            if(directory.name.equals(DIR_NAME))
                return directory;
        }
        return null;
    }

    private String[] getChildren(){
        String[] children = new String[currentDirectory.subDirectories.size()];
        int index = 0;

        for(Directory directory: currentDirectory.subDirectories){
            children[index] = directory.name;
            index++;

        }
        return children;
    }
}

class Directory{
    String name;
    Directory parent;
    ArrayList<Directory> subDirectories;

    public Directory(String name){
        this.name = name;
        this.subDirectories = new ArrayList<>();
    }
}