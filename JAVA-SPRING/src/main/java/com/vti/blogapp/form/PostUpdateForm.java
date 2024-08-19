package com.vti.blogapp.form;

import com.vti.blogapp.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostUpdateForm {
    @NotBlank(message = "Post Title must not be blank")
    @Length(max = 50, message = "Post tile has max 50")
    private String title;

    @NotBlank
    @Length(max = 150)
    private String content;

    @NotBlank
    @Length(max = 150)
    private String description;

    @NotNull
    @Pattern(regexp = "OPENING|CLOSED", message = "Post status must be OPENING or CLOSED")
    private String status;
}
