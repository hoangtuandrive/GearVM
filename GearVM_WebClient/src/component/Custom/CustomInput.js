import { Controller} from "react-hook-form";
import React from "react";
function CustomInput({ control,
    type,
    name,
    rules = {},
    placeholder,
   }) {
    return (  
        <Controller
        control={control}
        name={name}
        rules={rules}
        render={({ field: { onChange, onBlur, value},
        fieldState: { error },
        }) => (
          <>
          <div
          // style={[    
          //   { borderColor: error ? "red" : "#e8e8e8" },
          // ]}
        >
        <input
        type={type}
        value={value}
        onChange={onChange}
        onBlur={onBlur}
        placeholder={placeholder}
          />
          </div>
        {error && (
            <span style={{ color: "red", alignSelf: "stretch" }}>
              {error.message || "Error"}
            </span>
          )}
          </>
        )}
        
       />

    );
};

export default CustomInput;