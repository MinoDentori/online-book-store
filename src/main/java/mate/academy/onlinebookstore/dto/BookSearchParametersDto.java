package mate.academy.onlinebookstore.dto;

public record BookSearchParametersDto(String[] titles, String[] authors, String[] isbns) {
}
