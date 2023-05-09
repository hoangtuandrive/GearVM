import React, { useEffect } from "react";
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardHeader,
  MDBCardBody,
  MDBIcon,
  MDBTextArea,
  MDBCardFooter,
  MDBty,
} from "mdb-react-ui-kit";
import {
  MessageList,
  Message,
  TypingIndicator,
  MessageInput,
  MainContainer,
  ChatContainer,
  MessageGroup,
} from "@chatscope/chat-ui-kit-react";
import styles from "./ChatBox.module.scss";
import classNames from "classnames/bind";
import { faTimes, faPaperPlane } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import TextArea from "antd/es/input/TextArea";
import { useContext } from "react";
import { AppContext } from "../context/AppProvider";
import { useState } from "react";
import { chatgpt } from "../../chatgpt";
import "@chatscope/chat-ui-kit-styles/dist/default/styles.min.css";

const cx = classNames.bind(styles);

const systemMessage = {
  //  Explain things like you're talking to a software professional with 5 years of experience.
  role: "system",
  content:
    "Explain things like you're talking to a software professional with 2 years of experience.",
};

const ChatBox = ({ name }) => {
  const { setShowChat, messages, setMessages } = useContext(AppContext);

  // const [message, setMessage] = useState("");

  // const [messages, setMessages] = useState([
  //   {
  //     message: "Hello, I'm ChatGPT! Ask me anything!",
  //     sentTime: "just now",
  //     sender: "ChatGPT",
  //   },
  // ]);
  sessionStorage.setItem("chatBox", JSON.stringify(messages));

  const chatMessage = JSON.parse(sessionStorage.getItem("chatBox"));
  console.log(chatMessage);
  console.log(name);
  const review = name ? `Hãy mô tả chi tiết cho tôi sản phẩm ${name}` : null;
  // useEffect(() => {}, []);
  const [isTyping, setIsTyping] = useState(false);
  const [value, setValue] = useState(review);

  const handleMessage = async (message) => {
    const newMessage = {
      message,
      direction: "outgoing",
      sender: "user",
    };

    const newMessages = [...messages, newMessage];

    setMessages(newMessages);
    sessionStorage.setItem("chatBox", JSON.stringify(newMessages));
    // Initial system message to determine ChatGPT functionality
    // How it responds, how it talks, etc.

    setValue("");
    setIsTyping(true);
    // console.log(chatgpt);
    await processMessageToChatGPT(newMessages);
  };

  const handleHidenChat = () => {
    setShowChat(false);
  };

  async function processMessageToChatGPT(chatMessages) {
    // messages is an array of messages
    // Format messages for chatGPT API
    // API is expecting objects in format of { role: "user" or "assistant", "content": "message here"}
    // So we need to reformat

    let apiMessages = chatMessages.map((messageObject) => {
      let role = "";
      if (messageObject.sender === "ChatGPT") {
        role = "assistant";
      } else {
        role = "user";
      }
      return { role: role, content: messageObject.message };
    });
    console.log(chatMessages);
    console.log(apiMessages);

    // Get the request body set up with the model we plan to use
    // and the messages which we formatted above. We add a system message in the front to'
    // determine how we want chatGPT to act.
    const apiRequestBody = {
      model: "gpt-3.5-turbo",
      messages: [
        systemMessage, // The system message DEFINES the logic of our chatGPT
        ...apiMessages, // The messages from our chat with ChatGPT
      ],
    };

    await fetch("https://api.openai.com/v1/chat/completions", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + chatgpt,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(apiRequestBody),
    })
      .then((data) => {
        return data.json();
      })
      .then((data) => {
        console.log(data);
        setMessages([
          ...chatMessages,
          {
            message: data.choices[0].message.content,
            sender: "ChatGPT",
          },
        ]);
        setIsTyping(false);
        sessionStorage.setItem(
          "chatBox",
          JSON.stringify([
            ...chatMessages,
            {
              message: data.choices[0].message.content,
              sender: "ChatGPT",
            },
          ])
        );
      });
  }

  return (
    <MDBContainer className={cx("wrapChatBox")}>
      <MDBRow className="d-flex justify-content-flex-end">
        <MDBCol md="12" lg="12" xl="12">
          <MDBCard id="chat1" style={{ borderRadius: "15px" }}>
            <MDBCardHeader
              className="d-flex justify-content-between align-items-center p-3 bg-info text-white border-bottom-0"
              style={{
                borderTopLeftRadius: "15px",
                borderTopRightRadius: "15px",
              }}
            >
              <MDBIcon fas icon="angle-left" />
              <p className="mb-0 fw-bold">Chat Box</p>
              <MDBIcon>
                <FontAwesomeIcon
                  icon={faTimes}
                  onClick={handleHidenChat}
                  style={{ cursor: "pointer" }}
                />
              </MDBIcon>
            </MDBCardHeader>
            <div className={cx("ChatBody")}>
              <MainContainer>
                <ChatContainer>
                  <MessageList
                    scrollBehavior="smooth"
                    typingIndicator={
                      isTyping ? (
                        <TypingIndicator content="ChatGPT is typing" />
                      ) : null
                    }
                  >
                    {chatMessage?.map((message, i) => (
                      // <div className={cx("message")} key={i}>
                      //   <div className={cx("messImg")}>
                      //     {/* {message.sender === "user" ? null : (
                      //       <img
                      //         src={require("../../assets/chatbox.png")}
                      //         style={{ height: "50px", width: "50px" }}
                      //       />
                      //     )} */}
                      //   </div>
                      // <Message key={i} model={message} />
                      <Message
                        key={i}
                        model={{
                          direction: message.direction,
                          type: "custom",
                          sender: message.sender,
                        }}
                      >
                        <Message.CustomContent>
                          <span
                            className={cx(
                              `${
                                message.sender === "user"
                                  ? styles.user
                                  : styles.chatgpt
                              }`
                            )}
                          >
                            {message.message}
                          </span>
                        </Message.CustomContent>
                      </Message>
                      // </div>
                    ))}
                  </MessageList>
                  <MessageInput
                    attachButton={false}
                    placeholder="Type message here"
                    value={value}
                    onChange={(message) => {
                      setValue(message);
                    }}
                    onSend={handleMessage}
                    sendDisabled={false}
                  />
                </ChatContainer>
              </MainContainer>
            </div>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
};
export default ChatBox;
