package step.learning.servises.kdf;

public interface KdfService {
    String getDerivedKey(String password, String salt);
}
