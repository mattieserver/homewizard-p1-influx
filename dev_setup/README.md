```Flux
from(bucket: "P1")
  |> range(start: v.timeRangeStart, stop: v.timeRangeStop)
  |> filter(fn: (r) => r["_measurement"] == "meterdata")
  |> filter(fn: (r) => r["_field"] == "active_power_l1_w")
  |> toInt()
  |> aggregateWindow(every: v.windowPeriod, fn: last, createEmpty: false)
  |> yield(name: "last")
```