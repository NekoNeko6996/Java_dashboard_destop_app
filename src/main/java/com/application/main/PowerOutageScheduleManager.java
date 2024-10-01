package com.application.main;

import com.google.gson.reflect.TypeToken;
import com.application.models.PowerOutageSchedule;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

public class PowerOutageScheduleManager {
  private static final String URL = SettingManager.data.getBase_api().concat("/getPowerOutageSchedule");
  private static List<PowerOutageSchedule> data = new ArrayList<>();

  public static void load(String unitCode, Consumer<List<PowerOutageSchedule>> callback) {

    String body = App.gson.toJson(new UnitCode(unitCode));

    Http.postUrl(URL, body, null, (String responseText) -> {
      data = App.gson.fromJson(responseText, new TypeToken<List<PowerOutageSchedule>>() {
      }.getType());

      callback.accept(data);
    });
  }

  public static List<PowerOutageSchedule> getData() {
    return data;
  }

  static class UnitCode {
    public String unit_code;

    public UnitCode(String unitCode) {
      this.unit_code = unitCode;
    }

    public String getUnit_code() {
      return unit_code;
    }

    public void setUnit_code(String unitCode) {
      this.unit_code = unitCode;
    }
  }
}