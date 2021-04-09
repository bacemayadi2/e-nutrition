package e.nutrition.Services;

/**
 *
 * @author ALADIN
 */
public interface IService <T>
{
    public void Add(T t);
    public void Delete(T t);
    public void Update(T t);
    public void Display();
}
