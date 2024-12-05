package com.westeros.data.repositories;

public interface ICatalogData {
    ActorRepository getActors();
    CharacterRepository getCharacters();
    CompanyRepository getCompanies();
    CountryRepository getCountries();
    GenreRepository getGenres();
    LanguageRepository getLanguages();
    MoviesRepository getMovies();

}
