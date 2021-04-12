package e.nutrition.Services;

import e.nutrition.Models.Challenge;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ALADIN
 */
public interface IService <T>
{
    public void Add(T t);
    public void Delete(T t);
    public void Update(T t);
    public ObservableList<T> Display();
}
