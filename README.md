# binance-excel-report

Collects executed Orders from Binance and saves them in an Excel Spreadsheet.

# installation

````
scoop bucket add dem2k https://github.com/dem2k/scoop-bucket
scoop install binance-excel-report
````

# usage

open file ``config/properties.bat`` in app directory and store your binance api-key (only for reading). than open your terminal and run

````
binance-excel-report -sym btcusdt
````

this will export all your BTCUSDT orders when you ever bought some BTC.
