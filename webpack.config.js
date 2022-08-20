var path = require("path");

module.exports = {
  entry: "./src/main/js/src/index.js",
  devtool: "eval",
  cache: true,
  mode: "development",
  output: {
    path: __dirname,
    filename: "./src/main/resources/static/built/bundle.js",
  },
 
  module: {
    rules: [
      {
        test: path.join(__dirname, "."),
        use: [
          {
            loader: "babel-loader",
            options: {
              presets: ["@babel/preset-env", "@babel/preset-react"],
            },
          },
        ],
      },
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
      {
        test: /\.svg$/,
        use: [
          {
            loader: 'svg-url-loader',
            options: {
              limit: 10000,
            },
          },
        ],
      },
    ],
  },
};
