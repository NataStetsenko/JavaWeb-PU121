package step.learning.servises.kdf;

import step.learning.servises.HashService;

import javax.inject.Inject;

public class HashKdfService implements KdfService{
    private final HashService hashService;
@Inject
    public HashKdfService(HashService hashService) {
        this.hashService = hashService;
    }

    @Override
    public String getDerivedKey(String password, String salt) {
        return hashService.hash(String.format("%s-%s", password,salt));
    }
}
