package com.example.stageoneapi.model.api;


import com.example.stageoneapi.model.APIError;
import com.example.stageoneapi.model.NumberDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;


@RestController
class NumberDetailsController {

    private final View error;

    public NumberDetailsController(View error) {
        this.error = error;
    }

    @GetMapping("/api/classify-number")
    public Object classifyNumber(@RequestParam String number) {
        try {
            int num = Integer.parseInt(number);
            boolean isPrime = isPrime(num);
            boolean isPerfect = isPerfect(num);
            boolean isArmstrong = isArmstrong(num);
            String parity = (num % 2 == 0) ? "even" : "odd";
            int digitSum = getDigitSum(num);
            List<String> properties = new ArrayList<>();
            if (isArmstrong) properties.add("armstrong");
            properties.add(parity);
            String funFact = fetchFunFact(num);

            return new NumberDetails(num, isPrime, isPerfect, (ArrayList<String>) properties, digitSum, funFact);
        } catch (NumberFormatException e) {
            return new APIError("alphabet",true);
        }
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private boolean isPerfect(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) sum += num / i;
            }
        }
        return sum == num && num != 1;
    }

    private boolean isArmstrong(int num) {
        int sum = 0, temp = num, digits = String.valueOf(num).length();
        while (temp > 0) {
            sum += Math.pow(temp % 10, digits);
            temp /= 10;
        }
        return sum == num;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private String fetchFunFact(int num) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://numbersapi.com/" + num + "/math";
        return restTemplate.getForObject(url, String.class);
    }
}
