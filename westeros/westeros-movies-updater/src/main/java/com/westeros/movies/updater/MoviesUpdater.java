package com.westeros.movies.updater;


import com.westeros.data.model.*;
import com.westeros.data.repositories.ICatalogData;
import com.westeros.moviesclient.IMoviesClient;
import com.westeros.moviesclient.IMoviesDictionariesClient;
import com.westeros.moviesclient.contract.CompanySummaryDto;
import com.westeros.moviesclient.contract.MovieDto;
import com.westeros.moviesclient.contract.MovieSummaryDto;
import com.westeros.moviesclient.contract.PagedResultDto;
import com.westeros.moviesclient.contract.dictionaries.CountryDto;
import com.westeros.moviesclient.contract.dictionaries.GenreSummaryDto;
import com.westeros.moviesclient.contract.dictionaries.LanguageSummaryDto;
import com.westeros.tools.safeinvoker.SafeInvoking;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MoviesUpdater implements IUpdateMovies{

    private final ICatalogData dbCatalog;
    private final IMoviesClient moviesClient;
    private final SafeInvoking invoker;
    private final IMoviesDictionariesClient moviesDictionariesClient;

    public MoviesUpdater(ICatalogData dbCatalog, IMoviesClient moviesClient, SafeInvoking invoker, IMoviesDictionariesClient moviesDictionariesClient) {
        this.dbCatalog = dbCatalog;
        this.moviesClient = moviesClient;
        this.invoker = invoker;
        this.moviesDictionariesClient = moviesDictionariesClient;
    }

    @Override
    public void updateByDateRange(LocalDate from, LocalDate to) {
        List<CountryDto> countries = moviesDictionariesClient.getCountries();
        for(CountryDto country : countries)
        {
            Country tempCountry = new Country();
            BeanUtils.copyProperties(country, tempCountry);
            dbCatalog.getCountries().save(tempCountry);
        }

        List<GenreSummaryDto> genres = moviesDictionariesClient.getGenres();
        for(GenreSummaryDto genre : genres)
        {
            Genre tempGenre = new Genre();
            BeanUtils.copyProperties(genre, tempGenre);
            dbCatalog.getGenres().save(tempGenre);
        }


        List<LanguageSummaryDto> languages = moviesDictionariesClient.getLanguages();
        for(LanguageSummaryDto language : languages)
        {
            Language tempLanguage = new Language();
            BeanUtils.copyProperties(language, tempLanguage);
            dbCatalog.getLanguages().save(tempLanguage);
        }

        PagedResultDto respounce = moviesClient.getByDateRange(from, to);
        for(MovieSummaryDto one : respounce.getResults())
        {
            MovieDto movieDto = moviesClient.getMovie(one.getId());
            for(CompanySummaryDto companySummaryDto: movieDto.getProductionCompanies()) {
                Company company = new Company();
                BeanUtils.copyProperties(companySummaryDto, company);
                dbCatalog.getCompanies().save(company);
            }
            Movie movie = new Movie();
            BeanUtils.copyProperties(movieDto, movie);
            dbCatalog.getMovies().save(movie);
        }

    }
}
