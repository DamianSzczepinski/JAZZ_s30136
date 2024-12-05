package com.westeros.data.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class WesterosDataCatalog implements ICatalogData {
    private final ActorRepository actors;
    private final CharacterRepository characters;
    private final CompanyRepository companies;
    private final CountryRepository countries;
    private final GenreRepository genres;
    private final LanguageRepository languages;
    private final MoviesRepository movies;


    public WesterosDataCatalog(ActorRepository actors, CharacterRepository characters, CompanyRepository companies, CountryRepository countries, GenreRepository genres, LanguageRepository languages, MoviesRepository movies) {
        this.actors = actors;
        this.characters = characters;
        this.companies = companies;
        this.countries = countries;
        this.genres = genres;
        this.languages = languages;
        this.movies = movies;
    }

    @Override
    public ActorRepository getActors() {
        return actors;
    }

    @Override
    public CharacterRepository getCharacters() {
        return characters;
    }

    @Override
    public CompanyRepository getCompanies() {
        return companies;
    }

    public CountryRepository getCountries() {
        return countries;
    }

    @Override
    public GenreRepository getGenres() {
        return genres;
    }

    @Override
    public LanguageRepository getLanguages() {
        return languages;
    }

    @Override
    public MoviesRepository getMovies() {
        return movies;
    }
}
