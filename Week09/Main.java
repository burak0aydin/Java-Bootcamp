import java.util.*;

public class Main {
    public static void main(String[] args) {
        
    }
}

abstract class BaseEntity {
    private int id;
    private Date created;

    BaseEntity(int id) {
        this.id = id;
        this.created = new Date();

    }

    public int getId() { return this.id; }
    public Date getCreated() { return this.created; }
}

class User extends BaseEntity {
    private String username, email;

    User(int id, String username, String email) {
        super(id);

        this.username = username;
        this.email = email;
    }

    public String getUsername() { return this.username; }
    public void setUsername(String newUsername) { this.username = newUsername; }
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User [email=" + this.email + ", username=" + this.username + "]";
    }
}

class Role extends BaseEntity {
    private String name;

    Role(int id, String name) {
        super(id);

        this.name = name;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Role [name=" + this.name +"]";
    }
}

interface IGenericRepository <T extends BaseEntity> {
    void add(T element);
    T get(int id);
    void remove(int id);
    void update(T elemnet);
    int count();
}

abstract class GenericRepository <T extends BaseEntity> implements IGenericRepository<T> {
    HashSet<T> dataSource = new HashSet<T>();

    @Override
    public void add(T element) {
        for (T t : dataSource) {
            if (t.getId() == element.getId()) {
                throw new RuntimeException("Already exists");
            }
        }

        this.dataSource.add(element);
    }

    @Override
    public int count() {
        return dataSource.size();
    }

    @Override
    public T get(int id) throws RuntimeException {
        for (T t : dataSource) {
            if (t.getId() == id) {
                return t;
            }
        }

        throw new RuntimeException("Entity not Found");
    }

    @Override
    public void remove(int id) {
        this.dataSource.remove(this.get(id));
    }

    @Override
    public void update(T element) {
        T t = this.get(element.getId());
        t = element;
    }
}

interface IUserRepository extends IGenericRepository <User> {
    User getByEmail(String email);
}

class UserRepository extends GenericRepository <User> implements IUserRepository {

    @Override
    public User getByEmail(String email) {
        for (User user : dataSource) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        throw new RuntimeException("User not Found");
    }
}

interface IRoleRepository extends IGenericRepository <Role> {
    Role getByName(String email);
}

class RoleRepository extends GenericRepository <Role> implements IRoleRepository {

    @Override
    public Role getByName(String name) {
        for (Role role : dataSource) {
            if (role.getName().equals(name)) {
                return role;
            }
        }

        throw new RuntimeException("Role not found");
    }
}