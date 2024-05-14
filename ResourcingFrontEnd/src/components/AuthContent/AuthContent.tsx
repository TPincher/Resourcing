import React from "react";
import { request } from "../axiosHelper";

export default class AuthContent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
    };
  }

  componentDidMount() {
    request("GET", "/temps", {}).then((response) => {
      console.log(response);
      this.setState({ data: response.data });
    });
  }

  render() {
    return <div></div>;
  }
}
