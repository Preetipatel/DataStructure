package DataStructure;
import java.util.*;

public class Stack <T> {
    private ArrayList<T> data;

    public Stack()
    {
        data = new ArrayList<T>();
    }
    public void push(T t)
    {
        data.add(t);
    }
    public T top(){
        if(!data.isEmpty()){
            return data.get(data.size()-1);
        }
        else
            throw new EmptyStackException();
    }

    public boolean isEmpty(){
        if(data.isEmpty())
            return true;
        else
            return false;
    }
    public T pop(){
        if(data.isEmpty())
            throw new EmptyStackException();
        else{
            T popped_element;
            popped_element = data.get(data.size()-1);
            data.remove(data.size()-1);
            return  popped_element;
        }
    }
    public void display(){
        if(!isEmpty())
        {
            T x = pop();
            System.out.println(x + ", ");
            display();
            push(x);
        }
    }

    public int size(){
        return data.size();
    }
}

