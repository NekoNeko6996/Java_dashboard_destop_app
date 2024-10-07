package com.application.main;

import com.google.gson.reflect.TypeToken;
import com.application.models.PowerOutageSchedule;
import com.application.models.PowerOutageScheduleRegion;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PowerOutageScheduleManager {
  private static List<PowerOutageSchedule> data = new ArrayList<>();
  private static List<PowerOutageScheduleRegion> region = new ArrayList<>();

  public static void load(String unitCode, Consumer<List<PowerOutageSchedule>> callback) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String startDate = LocalDate.now().plusDays(-2).format(formatter);
    String endDate = LocalDate.now().plusDays(7).format(formatter);

    String body = App.gson.toJson(new Body(unitCode, startDate, endDate));

    Http.postUrl(SettingManager.data.getBase_api().concat("/getPowerOutageSchedule"), body, null,
        (String responseText) -> {
          data = App.gson.fromJson(responseText, new TypeToken<List<PowerOutageSchedule>>() {
          }.getType());

          callback.accept(data);
        });
  }

  public static void loadRegion(String provinceCode, Consumer<List<PowerOutageScheduleRegion>> callback) {

    String body = App.gson.toJson(new FetchRegion(provinceCode));
    Http.postUrl(SettingManager.data.getBase_api().concat("/getPowerOutageScheduleRegionList"), body, null,
        (String responseText) -> {
          region = App.gson.fromJson(responseText, new TypeToken<List<PowerOutageScheduleRegion>>() {
          }.getType());

          callback.accept(region);
        });
  }

  public static List<PowerOutageSchedule> getData() {
    return data;
  }

  static class FetchRegion {
    private String province_code;

    public FetchRegion(String provinceCode) {
      this.province_code = provinceCode;
    }

    public String getProvinceCode() {
      return province_code;
    }

    public void setProvinceCode(String provinceCode) {
      this.province_code = provinceCode;
    }
  }

  static class Body {
    private String unit_code;
    private String start_date;
    private String end_date;

    public Body(String unitCode, String startDate, String endDate) {
      this.unit_code = unitCode;
      this.start_date = startDate;
      this.end_date = endDate;
    }

    public String getUnitCode() {
      return unit_code;
    }

    public void setUnitCode(String unitCode) {
      this.unit_code = unitCode;
    }

    public String getStartDate() {
      return start_date;
    }

    public void setStartDate(String startDate) {
      this.start_date = startDate;
    }

    public String getEndDate() {
      return end_date;
    }

    public void setEndDate(String endDate) {
      this.end_date = endDate;
    }
  }
}