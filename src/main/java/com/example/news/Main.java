package com.example.news;

import com.example.news.model.Article;
import com.example.news.service.ArticleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext("com.example.news");
        ArticleService service = context.getBean(ArticleService.class);

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n...NEWS...");
            System.out.println("1.Latest News");
            System.out.println("2.Search News");
            System.out.println("3.By Category");
            System.out.println("4.By Date");
            System.out.println("5.Add Article");
            System.out.println("6.Delete Article");
            System.out.println("0.Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                System.out.println("Exit");
                context.close();
                break;
            }

            if (choice == 1) {
                System.out.println(service.getLatestNews());
            }
            else if (choice == 2) {
                System.out.print("Keyword: ");
                String key = sc.nextLine();
                System.out.println(service.searchNews(key));
            }
            else if (choice == 3) {
                System.out.print("Category: ");
                String cat = sc.nextLine();
                System.out.println(service.getByCategory(cat));
            }
            else if (choice == 4) {
                System.out.print("Date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(sc.nextLine());
                System.out.println(service.getByDate(date));
            }
            else if (choice == 5) {
                try {
                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Content: ");
                    String content = sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    System.out.print("Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());

                    Article article = new Article(title, content, category, date);
                    service.addArticle(article);

                    System.out.println("Added successfully");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (choice == 6) {
                System.out.print("ID: ");
                int id = sc.nextInt();
                service.deleteArticle(id);
                System.out.println("Deleted");
            }

            else {
                System.out.println("Invalid choice");
            }
        }
    }
}
